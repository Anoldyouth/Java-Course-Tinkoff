package edu.hw10;

import edu.hw10.annotations.Cache;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CacheProxyTest {
    private int count;
    private interface CacheTestMem {
        @Cache(persist = false)
        Integer testing(Integer num);
    }

    private interface CacheTestFile {
        @Cache(persist = true)
        Integer testing(Integer num);
    }

    private interface WithoutCache {
        Integer testing(Integer num);
    }

    @Test
    void memoryCaching() {
        count = 0;

        CacheTestMem cacheTest = new CacheTestMem() {
            @Override
            public Integer testing(Integer num) {
                count++;
                return num + 1;
            }
        };

        CacheTestMem test = CacheProxy.create(cacheTest, CacheTestMem.class);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void fileCaching() {
        count = 0;

        CacheTestFile cacheTest = new CacheTestFile() {
            @Override
            public Integer testing(Integer num) {
                count++;
                return num + 1;
            }
        };

        CacheTestFile test = CacheProxy.create(cacheTest, CacheTestFile.class);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void withoutCaching() {
        count = 0;

        WithoutCache cacheTest = new WithoutCache() {
            @Override
            public Integer testing(Integer num) {
                count++;
                return num + 1;
            }
        };

        WithoutCache test = CacheProxy.create(cacheTest, WithoutCache.class);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(test.testing(1)).isEqualTo(2);
        assertThat(count).isEqualTo(2);
    }
}
