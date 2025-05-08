package ru.netology.INT_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveProductIfExists() {
        // создаём репозиторий и добавляем два товара
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Phone", 10000));
        repo.add(new Product(2, "TV", 30000));

        // удаляем товар с ID 1
        repo.remove(1);

        // проверяем, что остался только второй товар
        Product[] expected = {new Product(2, "TV", 30000)};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemovingNonExistingProduct() {
        // создаём репозиторий и добавляем два товара
        ShopRepository repo = new ShopRepository();

        repo.add(new Product(1, "Phone", 10000));

        // пытаемся удалить товар с несуществующим ID
        assertThrows(NotFoundException.class, () -> repo.remove(100));
    }

    @Test
    void shouldAddProductSuccessfully() {
        // создаём репозиторий и добавляем один товар
        ShopRepository repo = new ShopRepository();

        // добавляем товар
        repo.add(new Product(1, "Phone", 10000));

        // проверяем, что товар добавился
        Product[] expected = {new Product(1, "Phone", 10000)};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    void shouldThrowAlreadyExistsExceptionOnDuplicateId() {
        // создаём репозиторий и добавляем товар с ID 1
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Phone", 10000));

        // пытаемся добавить ещё один товар с тем же ID
        assertThrows(AlreadyExistsException.class, () ->
                repo.add(new Product(1, "New Phone", 15000))
        );
    }
}