package tech.pegasys.pantheon.sidechains;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

public class GetEnodeData {

  static void getEnode(String host, int rpcHttpPort, int repeatCount) {
    final Logger logger = getLogger();
    final int repeat = 0;

    HttpClient httpClient = Vertx.vertx().createHttpClient();

    logger.info("Sending post request to " + rpcHttpPort);

    HttpClientRequest req = httpClient.post(rpcHttpPort, host, "/");

    req.exceptionHandler( err -> {
      if (repeatCount > 4) {
        logger.error("Something went wrong: " + err.getMessage());
        return;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      getEnode(host, rpcHttpPort, repeatCount + 1);
    });

    //noinspection deprecation
    req.handler(
            response -> {
              response.bodyHandler(body -> {
                logger.debug("Received " + body.toJsonObject());
                JsonObject jsonBody = body.toJsonObject();
                JsonObject jsonResult = jsonBody.getJsonObject("result");
                String x = jsonResult.getString("enode");
                logger.info(x);
              });
            });

    req.setChunked(true);
    req.putHeader("content-type", "application/json");
    req.write("{\"jsonrpc\":\"2.0\",\"method\":\"admin_nodeInfo\",\"params\":[],\"id\":53}");
    req.end();
  }
}
