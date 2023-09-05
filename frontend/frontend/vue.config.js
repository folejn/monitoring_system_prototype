const fs = require('fs')
module.exports = {
    devServer: {
      https: {
        https: true,
        key: fs.readFileSync('./ssl/project.key.pem'),
        cert: fs.readFileSync('./ssl/project.crt.pem'),
        passphrase: ''
      },
      port: 8081,
    }
  }