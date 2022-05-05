package dao;

import static org.junit.Assert.assertEquals;

import db.Storage;
import java.util.Map;
import java.util.Set;
import model.Fruit;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class StorageDaoImplTest {
    private static StorageDao storageDao;

    @BeforeClass
    public static void beforeClass() {
        storageDao = new StorageDaoImpl();
    }

    @Test
    public void update_add_fruit_ok() {
        Fruit apple = new Fruit("apple");
        storageDao.update(apple, 10);
        int expected = 10;
        int actual = Storage.dataBase.get(apple);
        assertEquals(expected, actual);
    }

    @Test
    public void update_add_more_ok() {
        Fruit apple = new Fruit("apple");
        Storage.dataBase.put(apple, 20);
        storageDao.update(apple, 20);
        int expected = 40;
        int actual = Storage.dataBase.get(apple);
        assertEquals(expected, actual);
    }

    @Test
    public void get_existing_ok() {
        Fruit apple = new Fruit("apple");
        Storage.dataBase.put(apple, 20);
        int expected = 20;
        int actual = storageDao.get(apple);
        assertEquals(expected, actual);
    }

    @Test
    public void get_not_existing_not_ok() {
        Fruit apple = new Fruit("apple");
        Fruit orange = new Fruit("orange");
        Storage.dataBase.put(apple, 20);
        Integer actual = storageDao.get(orange);
        Integer expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void get_null_not_ok() {
        storageDao.get(null);
    }

    @Test
    public void addAll_correct_ok() {
        Storage.dataBase.put(new Fruit("apple"), 10);
        Storage.dataBase.put(new Fruit("orange"), 13);
        Storage.dataBase.put(new Fruit("banana"), 40);
        Set<Map.Entry<Fruit, Integer>> entries = storageDao.getAll();
        int actual = entries.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        Storage.dataBase.clear();
    }
}