/*
 * Copyright 2018 ConsenSys AG.
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

package tech.pegasys.pantheon.ethereum.jsonrpc.websocket.methods;

import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.Logger;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.JsonRpcRequest;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcSuccessResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.websocket.methods.era.DomainInfo;
import tech.pegasys.pantheon.ethereum.jsonrpc.websocket.methods.era.EthereumRegistrationAuthorityFactory;
import tech.pegasys.pantheon.ethereum.jsonrpc.websocket.methods.era.Finder;
import tech.pegasys.pantheon.ethereum.jsonrpc.websocket.subscription.SubscriptionManager;
import tech.pegasys.pantheon.ethereum.jsonrpc.websocket.subscription.request.SubscriptionRequestMapper;
import tech.pegasys.pantheon.sidechains.SidechainConfigFactory;
import tech.pegasys.pantheon.sidechains.SidechainsConfiguration;
import tech.pegasys.pantheon.sidechains.SidechainsPantheonRunner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class CreateSideChain extends AbstractSubscriptionMethod {

    private final Logger logger;

    CreateSideChain(final SubscriptionManager subscriptionManager, final SubscriptionRequestMapper mapper) {
        super(subscriptionManager, mapper);
        this.logger = getLogger();
    }

    @Override
    public String getName() {
        return "sidechains_create";
    }

    @Override
    public JsonRpcResponse response(final JsonRpcRequest req) {
        Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/" + SidechainsConfiguration.infuraToken));
        EthereumRegistrationAuthorityFactory factory = new EthereumRegistrationAuthorityFactory(web3j);
        Finder finder = factory.finderAtAddressRead(SidechainsConfiguration.finderAddress);
        JsonObject response = new JsonObject();
        try {
            String address = getDomainInfoAddress(finder, String.valueOf(req.getParams()[0]));

            // get actual domain information
            DomainInfo domainInfo = factory.domainInfoAtAddressRead(address);
            response.put("ip", new String(domainInfo.getValue("ip"), StandardCharsets.UTF_8));
            response.put("port", new String(domainInfo.getValue("port"), StandardCharsets.UTF_8));

            // launch pantheon node
            SidechainConfigFactory.Config config = SidechainConfigFactory.createConfig();
            new SidechainsPantheonRunner().start(config);

            response.put("result", "Success");
        } catch (Exception e) {
            String msg = "Error encountered when reading ERA records using finder " + SidechainsConfiguration.finderAddress;
            logger.error(msg, e);
            response.put("msg", msg);
            response.put("error", e.toString());
        }

        response.put("time", System.currentTimeMillis())
                .put("thread", Thread.currentThread().getName());

        return new JsonRpcSuccessResponse(req.getId(), response.encodePrettily());
    }

    private String getDomainInfoAddress(Finder finder, String domainName) throws Exception {
        String p1DomainName = domainName.substring(domainName.indexOf(".") + 1);
        String p2DomainName = p1DomainName.substring(p1DomainName.indexOf(".") + 1);
        String p3DomainName = p2DomainName.substring(p2DomainName.indexOf(".") + 1);
        List<String> eraList = new ArrayList<>();
        eraList.add(SidechainsConfiguration.eraAddress);
        return finder.resolveDomain(eraList,
                domainName, p1DomainName, p2DomainName, p3DomainName);
    }
}
