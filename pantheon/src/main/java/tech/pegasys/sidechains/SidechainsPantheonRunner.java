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
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

public class SidechainsPantheonRunner {
  Process p = null;
  private final Logger logger;
  private Vertx vertx;

  public SidechainsPantheonRunner(Vertx vertx) {
    logger = getLogger();
    this.vertx = vertx;
  }

  public void start(String dataPath, String p2pPort, String rpcHttpPort) {

    String classpathStr = System.getProperty("java.class.path");

    try {
      String[] command = {
              "java",
              "-cp", classpathStr,
              "tech.pegasys.pantheon.Pantheon",
              "--network=dev",
              "--rpc-http-enabled=true",
              "--rpc-http-api=ADMIN, ETH, NET",
              "--discovery-enabled=true",
              "--data-path=" + dataPath,
              "--p2p-port=" + p2pPort,
              "--rpc-http-port=" + rpcHttpPort,
              "--bootnodes"
      };

      logger.info("Launching Pantheon sidechains instance with " + Arrays.toString(command));

      p = new ProcessBuilder()
              .command(command)
              .inheritIO()
              .start();
//      p.waitFor();
    } catch (IOException e) {
      System.out.println(Arrays.toString(e.getStackTrace()));
      //            e.printStackTrace();
    }
  }

  // NOTE: Couldn't get this working. Assuming we will have to make a web3j call
  // as a backup strategy.
  public void getEnode(String rpcHttpPort) {

    HttpClient httpClient = vertx.createHttpClient();

//    String url = "http://127.0.0.1:" + rpcHttpPort;
    logger.info("Sending post request to " + rpcHttpPort);

    HttpClientRequest req = httpClient.post(Integer.parseInt(rpcHttpPort), "127.0.0.1", "/");

    req.exceptionHandler( err -> {
      logger.error("Something ent wrong: " + err.getMessage());
    });

    req.handler(
            response -> {
              System.out.println("Received response with status code " + response.statusCode());
              response.bodyHandler(body -> {
                logger.info("Received " + body.toJsonObject());
                System.out.println("Received response with status code " + response.statusCode());
              });
            });
    req.putHeader("content-length", "1000");
    req.putHeader("content-type", "text/plain");
    req.write("{\"jsonrpc\":\"2.0\",\"method\":\"admin_nodeInfo\",\"params\":[],\"id\":53}");
    req.end();
  }

//  public void getAddress(String dataPath) {
//
//    String classpathStr = System.getProperty("java.class.path");
//
//    try {
//      String[] command = {
//              "java",
//              "-cp", classpathStr,
//              "tech.pegasys.pantheon.Pantheon",
//              "--network=dev",
//              "--rpc-http-enabled=true",
//              "--discovery-enabled=true",
//              "--data-path=" + dataPath,
//              "--bootnodes"
//      };
//
//      logger.info("Launching Pantheon sidechains instance with " + Arrays.toString(command));
//
//      p = new ProcessBuilder()
//              .command(command)
//              .inheritIO()
//              .start();
////      p.waitFor();
//    } catch (IOException e) {
//      System.out.println(Arrays.toString(e.getStackTrace()));
//      //            e.printStackTrace();
//    }
//  }

  public void stop() {
    p.destroy();
  }
}
