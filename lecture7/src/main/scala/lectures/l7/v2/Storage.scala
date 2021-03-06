package lectures.l7.v2

/*
 * Реализовать класс, который будет хранить в памяти элементы с типом V и ключом K
 * Элементы должны реализовывать интерфейс Item, с типом ключа K
 * Реализовать метод, который будет принимать только один элемент для сохранения, без явного указания ключа
 * Реализовать объект компаньен, который предоставит возможность создавать пустой MemoryStorage и MemoryStorage с одним элементом.
 *
 * В отличие от реализации в v1, данном случае MemoryStorage должен быть специализирован для конкретного типа элементов, например Dog, Cat
 * Способ использования смотреть в Main
 */

import lectures.l7.Item
trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

class MemoryStorage[T] extends Storage[T, Item[T]] {
  override def persist(id: T, item: Item[T]): Storage[T, Item[T]] = ???
}
