package lectures.l7.v1

/*
 * Реализовать класс, который будет хранить в памяти элементы с типом V и ключом K
 * Элементы должны реализовывать интерфейс Item, с типом ключа K
 * Реализовать метод, который будет принимать только один элемент для сохранения, без явного указания ключа
 * Реализовать объект компаньен, который предоставит возможность создавать пустой MemoryStorage и MemoryStorage с одним элементом.
 *
 * Способ использования смотреть в Main
 */


import lectures.l7.Item
trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

// сделать toString
class MemoryStorage[T](val storage: Map[T, Item[T]]) extends Storage[T, Item[T]] {

  def persist(item: Item[T]): MemoryStorage[T] = {
    persist(item.id, item)
  }

  override def persist(id: T, item: Item[T]): MemoryStorage[T] = {
    val newStorage = storage.updated(id, item)
    new MemoryStorage[T](newStorage)
  }

  override def toString = {
    var str: String = ""
    for ((k, v) <- storage) str += s"key: $k, val: $v \n"
    str
  }
}

object MemoryStorage {
  def apply[T](item: Item[T]): MemoryStorage[T] = {
    val storage = MemoryStorage.empty[T]()
    storage.persist(item)
  }
  def empty[T](): MemoryStorage[T] = new MemoryStorage[T](Map[T, Item[T]]())
}