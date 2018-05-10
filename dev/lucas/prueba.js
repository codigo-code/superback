const {OAuth2Client} = require('google-auth-library');
const http = require('http');
const url = require('url');
const querystring = require('querystring');
const opn = require('opn');
 
// Download your OAuth2 configuration from the Google
const keys = require('./token.json');
 
/**
 * Start by acquiring a pre-authenticated oAuth2 client.
 */
async function main() {
  try {
    const oAuth2Client = await getAuthenticatedClient();
    // Make a simple request to the Google Plus API using our pre-authenticated client. The `request()` method
    // takes an AxiosRequestConfig object.  Visit https://github.com/axios/axios#request-config.
    const url = 'https://www.googleapis.com/plus/v1/people?query=pizza';
    const res = await oAuth2Client.request({url})
    console.log(res.data);
  } catch (e) {
    console.error(e);
  }
  process.exit();
}