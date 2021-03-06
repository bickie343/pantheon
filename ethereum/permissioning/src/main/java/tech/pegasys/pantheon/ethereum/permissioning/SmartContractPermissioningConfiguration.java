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
package tech.pegasys.pantheon.ethereum.permissioning;

import tech.pegasys.pantheon.ethereum.core.Address;

public class SmartContractPermissioningConfiguration {
  private boolean smartContractNodeWhitelistEnabled;
  private Address smartContractAddress;

  public boolean isSmartContractNodeWhitelistEnabled() {
    return smartContractNodeWhitelistEnabled;
  }

  public void setSmartContractNodeWhitelistEnabled(
      final boolean smartContractNodeWhitelistEnabled) {
    this.smartContractNodeWhitelistEnabled = smartContractNodeWhitelistEnabled;
  }

  public Address getSmartContractAddress() {
    return smartContractAddress;
  }

  public void setSmartContractAddress(final Address smartContractAddress) {
    this.smartContractAddress = smartContractAddress;
  }
}
