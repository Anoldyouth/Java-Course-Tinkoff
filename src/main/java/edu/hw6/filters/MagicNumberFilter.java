package edu.hw6.filters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class MagicNumberFilter implements AbstractFilter {
    private final static int FF = 0xFF;

    private final byte[] bytes;

    public MagicNumberFilter(Object... bytes) {
        List<Byte> list = new ArrayList<>();

        for (Object oByte: bytes) {
            if (oByte instanceof Integer) {
                list.add(((Integer) oByte).byteValue());
            } else if (oByte instanceof Byte) {
                list.add((byte) ((byte) oByte & FF));
            } else if (oByte instanceof Character) {
                list.add((byte) ((Character) oByte).charValue());
            }
        }

        this.bytes = ArrayUtils.toPrimitive(list.toArray(new Byte[0]));
    }

    /**
     * Decides if the given directory entry should be accepted or filtered.
     *
     * @param path the directory entry to be tested
     * @return {@code true} if the directory entry should be accepted
     * @throws IOException If an I/O error occurs
     */
    @Override
    public boolean accept(Path path) throws IOException {
        int bufSize = bytes.length;

        try (FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(bufSize);

            int bytesRead = fileChannel.read(buffer);

            if (bytesRead != -1) {
                buffer.flip();

                byte[] readBytes = new byte[buffer.remaining()];
                buffer.get(readBytes);

                return Arrays.equals(readBytes, bytes);
            } else {
                return false;
            }
        }
    }
}
