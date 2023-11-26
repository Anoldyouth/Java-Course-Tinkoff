package edu.hw7;

import edu.hw7.factories.PersonFactory;
import static java.lang.Thread.sleep;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MapPersonDatabaseTest {
    private class SearchByName implements Runnable {
        private final String name;
        private List<Person> persons;

        public SearchByName(String name) {
            this.name = name;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            MapPersonDatabase db = new MapPersonDatabase();
            persons = db.findByName(name);
        }

        public List<Person> getPersons() {
            return persons;
        }
    }

    @Test
    void isSharedStorage() {
        MapPersonDatabase first = new MapPersonDatabase();
        MapPersonDatabase second = new MapPersonDatabase();

        Person person = new Person(1, "test", "test", "test");
        first.add(person);
        assertThat(second.findByName("test")).isEqualTo(List.of(person));
    }

    @Test
    void blockedWriteWhileSearching() throws InterruptedException {
        MapPersonDatabase db = new MapPersonDatabase();
        SearchByName firstSearch = new SearchByName("test");
        SearchByName secondSearch = new SearchByName("test");
        int count = 1_000_000;

        for (int i = 1; i <= count; i++) {
            db.add(PersonFactory.newFactory().withId(i).withName("test").make());
        }
        Person needToAdd = PersonFactory.newFactory().withId(count + 1).withName("test").make();

        Thread thread = new Thread(firstSearch);
        thread.start();

        assertThat(thread.isAlive()).isTrue();
        sleep(1000);
        db.add(needToAdd);
        assertThat(thread.isAlive()).isFalse();

        assertThat(firstSearch.getPersons()).doesNotContain(needToAdd);

        Thread secondThread = new Thread(secondSearch);
        secondThread.start();
        secondThread.join();

        assertThat(secondSearch.getPersons()).contains(needToAdd);
    }
}
