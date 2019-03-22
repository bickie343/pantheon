package tech.pegasys.pantheon.sidechains;

/**
 * Create configurations for instances of sidechain nodes avoiding port and
 * data directory collisions.
 */
public class SidechainConfigFactory {
  private static int configCount = 0;
  private static final int incrementMultiplier = 4;

  public static class Config {
    String dataPath;
    int p2pPort;
    int rpcHttpPort;
    int rpcWsPort;
    int scRpcHttpPort;
    int networkId;
  }

  public static synchronized Config createConfig() {
    Config config = new Config();

    configCount += 1;

    config.dataPath = SidechainsConfiguration.sidechainsDataPath + "/" + configCount;
    config.p2pPort = SidechainsConfiguration.sidechainsP2pPort + configCount * incrementMultiplier;
    config.rpcHttpPort = SidechainsConfiguration.sidechainsRpcHttpPort + configCount * incrementMultiplier;
    config.rpcWsPort = SidechainsConfiguration.sidechainsRpcWsPort + configCount * incrementMultiplier;
    config.scRpcHttpPort = SidechainsConfiguration.sidechainsScRpcHttpPort + configCount * incrementMultiplier;
    config.networkId = (int)(Math.random() * 5000000 + 100);

    return config;
  }
}
