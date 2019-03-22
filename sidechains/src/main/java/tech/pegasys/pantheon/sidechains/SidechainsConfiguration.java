package tech.pegasys.pantheon.sidechains;

import org.web3j.abi.datatypes.Array;

public class SidechainsConfiguration {
  public static final int SIDECHAINS_DEFAULT_PORT = 8080;
  public static final String DEFAULT_ADDRESS = "";

  // Sidechains feature is enabled
  public static boolean isEnabled = false;

  // ERA access parameters
  public static String finderAddress;
  public static String eraAddress;
  public static String infuraToken;

  // Additional settings for this node
  public static String network;
  public static String discoveryEnabled;
  public static String isRpcHttpEnabled;
  public static int rpcHttpPort;
  public static Array rpcHttpApi;
  public static boolean isRpcWsEnabled;
  public static int rpcWsPort;
  public static Array rpcWsApi;
  public static int p2pPort;

  // Setup for created sidechain nodes
  public static String sidechainsDataPath;
  public static int sidechainsP2pPort;
  public static int sidechainsRpcHttpPort;
  public static int sidechainsRpcWsPort;
  public static int sidechainsScRpcHttpPort;
}
