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

import java.io.IOException;
import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

public class SidechainsCreatorRunner {

  private final Logger logger;
  Process p = null;

  public SidechainsCreatorRunner() {
    this.logger = getLogger();
  }

  public void start(int port) {
//    String path =
//        SidechainsCreatorRunner.class
//            .getProtectionDomain()
//            .getCodeSource()
//            .getLocation()
//            .getPath();
//    String classPath = "-classpath \"/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/lib/tools.jar:/Users/martinbosticky/IdeaProjects/pantheon/pantheon/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/pantheon/out/production/resources:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/trie/out/production/classes:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.guava/guava/27.0.1-jre/bd41a290787b5301e63929676d792c507bbc00ae/guava-27.0.1-jre.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/info.picocli/picocli/3.9.2/60911f3b35e76b442d47e764758f3ecc3aa876c2/picocli-3.9.2.jar:/Users/martinbosticky/IdeaProjects/pantheon/services/queue/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/services/queue/out/production/resources:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-web/3.6.3/8c54a11c2608d4ed041945df4e0d579ae035c0db/vertx-web-3.6.3.jar:/Users/martinbosticky/IdeaProjects/pantheon/services/util/out/production/classes:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-web-common/3.6.3/d1cb5ac02370a58588840f58599f734fd485360/vertx-web-common-3.6.3.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-auth-common/3.6.3/d389212f5eb979428812550896ec5a0c6a045ca7/vertx-auth-common-3.6.3.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-core/3.6.3/90f3e8b57a42571c4aa769a1f50eed93b07b03f/vertx-core-3.6.3.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/net.consensys.cava/cava-toml/0.5.0/84f87a7a0612fc8fd29ffb8fe2b93bd4fd96c04e/cava-toml-0.5.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-core/2.11.1/592a48674c926b01a9a747c7831bcd82a9e6d6e4/log4j-core-2.11.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.11.1/268f0fe4df3eefe052b57c87ec48517d64fb2a10/log4j-api-2.11.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.springframework.security/spring-security-crypto/5.1.3.RELEASE/22835123bf86f94c4756e470a2421acce6fee8fe/spring-security-crypto-5.1.3.RELEASE.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.3.21/d0634d54452abc421db494ad32dd215e6591c49f/kotlin-stdlib-jdk8-1.3.21.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-auth-jwt/3.6.2/7d7bf806b4f28955b91571f4f370fa1b35c69224/vertx-auth-jwt-3.6.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk15on/1.60/bd47ad3bd14b8e82595c7adaa143501e60842a84/bcprov-jdk15on-1.60.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-jwt/3.6.2/6c3eac386a498c4719e81ee9246ba07dccb1eb68/vertx-jwt-3.6.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.guava/failureaccess/1.0.1/1dcf1de382a0bf95a3d8b0849546c88bac1292c9/failureaccess-1.0.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/b421526c5f297295adef1c886e5246c39d4ac629/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.code.findbugs/jsr305/3.0.2/25ea2e8b0c338a877313bd4672d3fe056ea78f0d/jsr305-3.0.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.checkerframework/checker-qual/2.5.2/cea74543d5904a30861a61b4643a5f2bb372efc4/checker-qual-2.5.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.errorprone/error_prone_annotations/2.2.0/88e3c593e9b3586e1c6177f89267da6fc6986f0c/error_prone_annotations-2.2.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.google.j2objc/j2objc-annotations/1.1/976d8d30bebc251db406f2bdb3eb01962b5685b3/j2objc-annotations-1.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.codehaus.mojo/animal-sniffer-annotations/1.17/f97ce6decaea32b36101e37979f8b647f00681fb/animal-sniffer-annotations-1.17.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-codec-http2/4.1.30.Final/2da92f518409904954d3e8dcc42eb6a562a70302/netty-codec-http2-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-handler/4.1.30.Final/ecc076332ed103411347f4806a44ee32d9d9cb5f/netty-handler-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-handler-proxy/4.1.30.Final/1baa1568fa936caddca0fae96fdf127fd5cbad16/netty-handler-proxy-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient_pushgateway/0.6.0/444ba7856dbe8de498f7188901aa5e6dfc5d7648/simpleclient_pushgateway-0.6.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient_common/0.6.0/8b4f119cfdff67a02a066e6e519bb2bab0a2a1b/simpleclient_common-0.6.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-resolver-dns/4.1.30.Final/3f4bcf2e9fff1361ac9ad0bd27a10a1b31399294/netty-resolver-dns-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-codec-http/4.1.30.Final/1384c630e8a0eeef33ad12a28791dce6e1d8767c/netty-codec-http-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient_hotspot/0.6.0/2703b02c4b2abb078de8365f4ef3b7d5e451382d/simpleclient_hotspot-0.6.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-codec-socks/4.1.30.Final/ea272e3bb281d3a91d27278f47e61b4de285cc27/netty-codec-socks-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient/0.6.0/26073e94cbfa6780e10ef524e542cf2a64dabe67/simpleclient-0.6.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.xerial.snappy/snappy-java/1.1.7.2/307b286efd119ad2c6d4291128bf110bddc68088/snappy-java-1.1.7.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-codec-dns/4.1.30.Final/7d28ce324f6cd5ae4ddd7f3e5027e2a7f126740b/netty-codec-dns-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-codec/4.1.30.Final/515c8f609aaca28a94f984d89a9667dd3359c1b1/netty-codec-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.squareup.okhttp3/okhttp/3.12.1/dc6d02e4e68514eff5631963e28ca7742ac69efe/okhttp-3.12.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.rocksdb/rocksdbjni/5.17.2/bca52276cabe91a3b97cc18e50fa2eabc2986f58/rocksdbjni-5.17.2.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-transport/4.1.30.Final/3d27bb432a3b125167ac161b26415ad29ec17f02/netty-transport-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-buffer/4.1.30.Final/597adb653306470fb3ec1af3c0f3f30a37b1310a/netty-buffer-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-resolver/4.1.30.Final/5106fd687066ffd712e5295d32af4e2ac6482613/netty-resolver-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.netty/netty-common/4.1.30.Final/5dca0c34d8f38af51a2398614e81888f51cf811a/netty-common-4.1.30.Final.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.9.8/11283f21cc480aa86c4df7a0a3243ec508372ed2/jackson-databind-2.9.8.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.9.8/f5a654e4675769c716e5b387830d19b501ca191/jackson-core-2.9.8.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/io.vertx/vertx-bridge-common/3.6.3/cb466c19fbd7d4e88a7a56de8dd68109a5265d1b/vertx-bridge-common-3.6.3.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.antlr/antlr4-runtime/4.7.1/946f8aa9daa917dd81a8b818111bec7e288f821a/antlr4-runtime-4.7.1.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.3.21/d207ce2c9bcf17dc8e51bab4dbfdac4d013e7138/kotlin-stdlib-jdk7-1.3.21.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.3.21/4bcc2012b84840e19e1e28074284cac908be0295/kotlin-stdlib-1.3.21.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.9.0/7c10d545325e3a6e72e06381afe469fd40eb701/jackson-annotations-2.9.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.3.21/f30e4a9897913e53d778f564110bafa1fef46643/kotlin-stdlib-common-1.3.21.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar:/Users/martinbosticky/.gradle/caches/modules-2/files-2.1/com.squareup.okio/okio/1.15.0/bc28b5a964c8f5721eb58ee3f3c47a9bcbf4f4d8/okio-1.15.0.jar:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/eth/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/consensus/ibftlegacy/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/metrics/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/config/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/config/out/production/resources:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/core/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/core/out/production/resources:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/rlp/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/services/kvstore/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/services/kvstore/out/production/resources:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/blockcreation/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/consensus/clique/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/jsonrpc/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/consensus/common/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/p2p/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/ethereum/permissioning/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/crypto/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/enclave/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/util/out/production/classes:/Users/martinbosticky/IdeaProjects/pantheon/consensus/ibft/out/production/classes:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar\"";
    String classpathStr = System.getProperty("java.class.path");

//    System.out.println("SidechainsCreatorRunner: launching wih path " + classpathStr);
    try {
      String[] command = {
              "java",
              "-cp", classpathStr,
              "tech.pegasys.sidechains.Sidechains",
              "--port", Integer.toString(port)
      };

      logger.info("Launching sidechains process with " + Arrays.toString(command));

      p =
          new ProcessBuilder()
              .command(command)
              .inheritIO()
              .start();
//      p.waitFor();
    } catch (IOException e) {
//      System.out.println(Arrays.toString(e.getStackTrace()));
      logger.error(e.getStackTrace());
      //            e.printStackTrace();
    }
  }

  public void stop() {
    p.destroy();
  }
}
