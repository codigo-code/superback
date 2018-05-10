$(function() {

    // App configuration
var authEndpoint = 'https://login.microsoftonline.com/common/oauth2/v2.0/authorize?';
var redirectUri = 'http://localhost:8081';
var appId = '43cc6ead-a370-4d11-8e4e-bc1fb9b0206e';
var scopes = 'openid profile User.Read Mail.Read';


    // Check for browser support for sessionStorage
    if (typeof(Storage) === 'undefined') {
      render('#unsupportedbrowser');
      return;
    }
  
    // Check for browser support for crypto.getRandomValues
    var cryptObj = window.crypto || window.msCrypto; // For IE11
    if (cryptObj === undefined || cryptObj.getRandomValues === 'undefined') {
      render('#unsupportedbrowser');
      return;
    }
  
    render(window.location.hash);
  
    $(window).on('hashchange', function() {
      render(window.location.hash);
    });
  
    function render(hash) {
  
      var action = hash.split('=')[0];
  
      // Hide everything
      $('.main-container .page').hide();
  
      var isAuthenticated = false;
  
      var pagemap = {
  
        // Welcome page
        '': function() {
          renderWelcome(isAuthenticated);
        },
  
        // Receive access token
  
        // Signout
  
        // Error display
  
        // Display inbox
  
        // Shown if browser doesn't support session storage
        '#unsupportedbrowser': function () {
          $('#unsupported').show();
        }
      }
  
      if (pagemap[action]){
        pagemap[action]();
      } else {
        // Redirect to home page
        window.location.hash = '#';
      }
    }
  
    function setActiveNav(navId) {
      $('#navbar').find('li').removeClass('active');
      $(navId).addClass('active');
    }
  
    function renderWelcome(isAuthed) {
      if (isAuthed) {
        $('#username').text(sessionStorage.userDisplayName);
        $('#logged-in-welcome').show();
        setActiveNav('#home-nav');
      } else {
        $('#connect-button').attr('href', buildAuthUrl());
        $('#signin-prompt').show();
      }
    }
  
    // OAUTH FUNCTIONS =============================
  
    var msalconfig = {
        clientID: "43cc6ead-a370-4d11-8e4e-bc1fb9b0206e",
        redirectUri: location.origin
    };

    // OUTLOOK API FUNCTIONS =======================
  
    function buildAuthUrl() {
        // Generate random values for state and nonce
        sessionStorage.authState = guid();
        sessionStorage.authNonce = guid();
      
        var authParams = {
          response_type: 'id_token token',
          client_id: appId,
          redirect_uri: redirectUri,
          scope: scopes,
          state: sessionStorage.authState,
          nonce: sessionStorage.authNonce,
          response_mode: 'fragment'
        };
      
        return authEndpoint + $.param(authParams);
      }

    // HELPER FUNCTIONS ============================
  function guid() {
  var buf = new Uint16Array(8);
  cryptObj.getRandomValues(buf);
  function s4(num) {
    var ret = num.toString(16);
    while (ret.length < 4) {
      ret = '0' + ret;
    }
    return ret;
  }
  return s4(buf[0]) + s4(buf[1]) + '-' + s4(buf[2]) + '-' + s4(buf[3]) + '-' +
    s4(buf[4]) + '-' + s4(buf[5]) + s4(buf[6]) + s4(buf[7]);
}


function handleTokenResponse(hash) {
    // clear tokens
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('idToken');
  
    var tokenresponse = parseHashParams(hash);
  
    // Check that state is what we sent in sign in request
    if (tokenresponse.state != sessionStorage.authState) {
      sessionStorage.removeItem('authState');
      sessionStorage.removeItem('authNonce');
      // Report error
      window.location.hash = '#error=Invalid+state&error_description=The+state+in+the+authorization+response+did+not+match+the+expected+value.+Please+try+signing+in+again.';
      return;
    }
  
    sessionStorage.authState = '';
    sessionStorage.accessToken = tokenresponse.access_token;
  
    // Get the number of seconds the token is valid for,
    // Subract 5 minutes (300 sec) to account for differences in clock settings
    // Convert to milliseconds
    var expiresin = (parseInt(tokenresponse.expires_in) - 300) * 1000;
    var now = new Date();
    var expireDate = new Date(now.getTime() + expiresin);
    sessionStorage.tokenExpires = expireDate.getTime();
  
    sessionStorage.idToken = tokenresponse.id_token;
  
    // Redirect to home page
    window.location.hash = '#';   
  }
  
  });