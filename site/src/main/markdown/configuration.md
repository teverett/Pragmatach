Configuration
------------------------

Configuration for the Pragmatach framework is contained in the file

`src\main\resources\pragmatach.configuration`

Basic configuration values include:

`pragmatach.cookie.cryptokey` The crypto key for AES cookie encryption

`pragmatach.adminapp.username` The username to login to the Adminstration console  

`pragmatach.adminapp.password` The password to login to the Administration console

`pragmatach.applicationuser` The HTTP Basic Auth login username

`pragmatach.applicationpassword` The HTTP Basic Auth login password

`pragmatach.applicationrealm` The HTTP Basic Auth realm

Plugins also use the configutation file, for example the JCR plugin requires these configuration settings:

`jcr.url` The URL of the JCR server

`jcr.workspace` The JCR workspace name

`jcr.username` The JCR username

`jcr.password` The JCR password
