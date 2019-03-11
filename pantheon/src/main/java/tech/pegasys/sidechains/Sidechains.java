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

import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Sidechains {

  public static void main(final String... args) {

    Logger logger = getLogger();

    logger.info("Process started with parameters " + Arrays.toString(args));

    if (args.length != 2) {
      logger.error("Only 2 arguments expected");
      return;
    }

    if (!args[0].equals("--port")) {
      logger.error("--port argument expected but found " + args[0]);
      return;
    }

    logger.info("Process starting event loop...");
    int port = Integer.parseInt(args[1]);
    HttpServerEventLoop server = new HttpServerEventLoop();
    server.runLoop(port);
  }
}
