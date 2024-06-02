module Java9Modules {
    requires java.base;
    requires java.logging;
    requires java.net.http;
    requires lombok;
    exports org.oceanobe.process.api;
}