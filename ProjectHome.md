# Android Web Service Client #

## Introduction ##

The "Android Web Service Client" provides a way to generate web service client stub and lightweight, easy-to-use and efficient library. This helps the user to consume the web service most easy way something like "call a local method".


This is a web Service client-stub generator using the CXF library. This sends the request SOAP xml to the web service and in returns receives response SOAP xml and uses the pull parser to parse that xml and makes the response object.

But, when using this library what user feels is just instantiate a service and call its method with some request parameter just like to call some local method.

## Support ##
For more information check the wiki [pages](http://code.google.com/p/android-ws-client/wiki/androidWSClientHowToV12)  and the full step by step [documentation](http://android-ws-client.googlecode.com/files/Step-by-step-guide-android-ws-client-1.2.0.pdf).

## Releases ##
20th June, 2012
### Version 1.2.0 ###

Some issues are fixed in this release and suggested to use this release rather than 1.1.0. Some Changes are :

  * Jaxbelement issues in the client stub - now it is fixed not to generate "JAXBElement" attribute in client stub java classes.
  * Make the "CXF distribution" outside of the project.
  * Fixed some issues related to soap request and response xml generation.
  * Unit tests added for some complex request and responses. The unit test project is available in the source repository named "AndroidWSProxyTest".

**Known Issues**
  * "Https webservice" is not supported.
  * Only workable in windows system.

### Version 1.1.0 ###
Deprecated version. Encouraged to use the latest version.



