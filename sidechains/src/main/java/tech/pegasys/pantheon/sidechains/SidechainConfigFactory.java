package tech.pegasys.pantheon.sidechains;

/**
 * Create configurations for instances of sidechain nodes avoiding port and
 * data directory collisions.
 */
class SidechainConfigFactory {
  private static int configCount = 0;
  private static final int incrementMultiplier = 3;

  static class Config {
    String dataPath;
    int p2pPort;
    int rpcHttpPort;
  }

  static synchronized Config createConfig() {
    Config config = new Config();

    configCount += 1;

    config.dataPath = SidechainsConfiguration.sidechainsDataPath + "/" + configCount;
    config.p2pPort = SidechainsConfiguration.sidechainsP2pPort + configCount * incrementMultiplier;
    config.rpcHttpPort = SidechainsConfiguration.sidechainsHttpPort + configCount * incrementMultiplier;

    return config;
  }
}
