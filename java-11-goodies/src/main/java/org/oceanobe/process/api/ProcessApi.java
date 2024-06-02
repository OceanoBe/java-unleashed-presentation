package org.oceanobe.process.api;

import java.io.IOException;

public interface ProcessApi {

    void printProcessInfo();

    void printChildProcessInfo() throws IOException;
}
