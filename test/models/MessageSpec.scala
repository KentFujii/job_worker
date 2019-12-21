package models.connect

import org.scalatest.FunSuite

class RoomTest extends FunSuite {
  test("Roomのid=1407を取得できる") {
    val room: Room = using(DB(ConnectionPool('connect).borrow())) { db =>
      db.readOnly { implicit session =>
        Room.findById(1407).get;
      }
    }
    println(room)
    assert(
      room.id == 1407
    )
  }
}
