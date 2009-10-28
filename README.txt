This package is intended to be compatible with J2ME, GWT and standard java
so 
No Generics, Collections or StringBuilder

Note that GWT requires Serlization which isn't available on J2ME
so to make a I_TemplateParams use the writeXml readXml to pass a String 
representation of the I_Template params to GWT's RPC methods