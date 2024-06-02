package org.oceanobe.process.impl;

import org.oceanobe.process.api.ProcessApi;

import java.io.IOException;
import java.time.ZoneId;
import java.util.stream.Stream;

public class ProcessApiImpl implements ProcessApi {

    /**
     * Get current process using {@link ProcessHandle#current()} method.
     * Then using {@link ProcessHandle.Info} interface, we're getting the process information of the current process.
     */
    @Override
    public void printProcessInfo() {
        String defaultMessage = "Not Present";
        ProcessHandle currentProcess = ProcessHandle.current();
        ProcessHandle.Info info = currentProcess.info();
        System.out.printf("Process ID : %s%n", currentProcess.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(defaultMessage));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(defaultMessage));

        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()).orElse(defaultMessage));

        System.out.printf("Arguments : %s%n",
                info.arguments().map(a -> String.join(" ", a)).orElse(defaultMessage));

        System.out.printf("User : %s%n", info.user().orElse(defaultMessage));
    }

    @Override
    public void printChildProcessInfo() throws IOException {
        for (int i = 0; i < 3; i++) {
            ProcessBuilder processBuilder
                    = new ProcessBuilder("java", "-version");
            processBuilder.inheritIO().start();
        }
        Stream<ProcessHandle> childProcesses = ProcessHandle.current().children();
        String defaultMessage = "Not Present";
        childProcesses.filter(ProcessHandle::isAlive).forEach(
                childProcess ->{
                    System.out.printf("Child Process ID : %s%n", childProcess.pid());
                    System.out.printf("Child Command name : %s%n", childProcess.info().command().orElse(defaultMessage));
                    System.out.printf("Child Command line : %s%n", childProcess.info().commandLine().orElse(defaultMessage));
                }
        );
    }
}
