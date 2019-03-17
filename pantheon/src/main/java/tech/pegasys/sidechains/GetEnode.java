package tech.pegasys.sidechains;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

public class GetEnode {

  private final Logger logger;
  private Vertx vertx;

  public GetEnode(Vertx vertx) {
    logger = getLogger();
    this.vertx = vertx;
  }

  public void getEnode(String host, String rpcHttpPort) {

    HttpClient httpClient = vertx.createHttpClient();

    logger.info("Sending post request to " + rpcHttpPort);

    HttpClientRequest req = httpClient.post(Integer.parseInt(rpcHttpPort), host, "/");

    req.exceptionHandler( err -> {
      logger.error("Something ent wrong: " + err.getMessage());
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
