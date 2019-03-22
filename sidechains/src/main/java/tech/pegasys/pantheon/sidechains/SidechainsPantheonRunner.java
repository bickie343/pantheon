package tech.pegasys.pantheon.sidechains;/*
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

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Launch sidechains Pantheon node.
 */
public class SidechainsPantheonRunner {
  Process p = null;
  private final Logger logger;
  private int sidechainCount = 0;

  public SidechainsPantheonRunner() {
    logger = getLogger();
  }

  public String start() {

    String classpathStr = System.getProperty("java.class.path");
    SidechainConfigFactory.Config config = SidechainConfigFactory.createConfig();

    String[] command = {
            "java",
            "-cp", classpathStr,
            "tech.pegasys.pantheon.Pantheon",
            "--network=" + SidechainsConfiguration.network,
            "--rpc-http-enabled=true",
            "--rpc-http-api=ADMIN, ETH, NET",
            "--discovery-enabled=true",
            "--data-path=" + config.dataPath,
            "--p2p-port=" + config.p2pPort,
            "--rpc-http-port=" + config.rpcHttpPort
//              "--bootnodes"
    };

    try {
      logger.info("Launching Pantheon sidechains instance with " + Arrays.toString(command));

      p = new ProcessBuilder()
              .command(command)
              .inheritIO()
              .start();
    } catch (IOException e) {
      logger.error(e.getStackTrace());
      return "Error";
    }

    String[] params = Arrays.copyOfRange(command, 3, command.length-1);
    return Arrays.toString(params);
  }

  public void stop() {
    p.destroy();
  }
}
