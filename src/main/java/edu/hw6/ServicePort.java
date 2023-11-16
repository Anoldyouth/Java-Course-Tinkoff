package edu.hw6;

import org.jetbrains.annotations.Nullable;

public record ServicePort(Protocol protocol, int port, @Nullable String service) {
}
