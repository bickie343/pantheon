# Sidechains demo

1. Open this repository in IntelliJ
2. Launch instance of Pantheon using command `pantheon --config-file sidechains/dev_node1.toml`
3. Request sidechain creation from our Sidechain Creator node
  - POSTMAN
    -  `{"jsonrpc":"2.0","method":"sc_create","params":["node4"],"id":53}`
  - wscat
    - `wscat -c ws://127.0.0.1:30013`
    - `{"jsonrpc":"2.0","method":"sc_create","params":["node4"],"id":53}`
    - `wscat -c ws://127.0.0.1:30013 -x "{\"jsonrpc\":\"2.0\",\"method\":\"sc_create\",\"params\":[\"node4\"],\"id\":53}"`
4. This sends message to the Pantheon node to start a side chain