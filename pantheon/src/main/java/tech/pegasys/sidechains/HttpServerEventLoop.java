/*
 * Copyright 2019 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.sidechains;


import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

public class HttpServerEventLoop {

  private final Logger logger;

  public HttpServerEventLoop() {
    this.logger = getLogger();
  }

  public void runLoop(int port) {
    Vertx vertx = Vertx.vertx();

    vertx
        .createHttpServer()
        .requestHandler(
            req -> {
              req.bodyHandler(breq -> {
                String name = req.getParam("name");
                String message = "hello " + (name != null ? name : "world") + "!";

                JsonObject x = breq.toJsonObject();

                if (x.getString("method").equals("sc_createSidechain")) {
                  SidechainsPantheonRunner runner = new SidechainsPantheonRunner(vertx);
                  String rpcHttpPort = "40311";
                  runner.start("sidechain2", "40301", rpcHttpPort);

                  // Try to read enode contact information for the node I just created
//                  try {
//                    Thread.sleep(5000);
//                  } catch (InterruptedException e) {
//                    e.printStackTrace();
//                  }
//
//                  runner.getEnode(rpcHttpPort);
                }

                JsonObject json =
                        new JsonObject()
                                .put("message", message)
                                .put("time", System.currentTimeMillis())
                                .put("thread", Thread.currentThread().getName())
                        .put("request", x);
                req.response()
                        .putHeader("Content-Type", "application/json; charset=UTF8")
                        .end(json.encodePrettily());
              });
            })
        .listen(port);

    if (logger != null) {
      logger.info("Sidechains server started on port " + port);
    }
  }
}

//    SidechainsCreatorRunner sidechainsCreatorRunner = new SidechainsCreatorRunner();
//    sidechainsCreatorRunner.start();
//    System.out.println("Sidechains: starting server...");
//    SidechainsCreatorRunner runner = new SidechainsCreatorRunner();
//    runner.start();
