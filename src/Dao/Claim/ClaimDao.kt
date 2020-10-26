package org.csuf.cspc411.Dao.Claim

import org.csuf.cspc411.Dao.Dao
import org.csuf.cspc411.Dao.Database
import java.util.*

class ClaimDao : Dao() {

    fun addClaim(pObj : Claim) {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "insert into claim (id, title, date, isSolved) values ('${pObj.id}', '${pObj.title}', '${pObj.date}', '${pObj.isSolved}')"

        println(sqlStmt)

        // 3. submit the sql statement
        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Claim> {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "select id, title, date, isSolved from claim"

        // 3. submit the sql statement
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // 4. Convert into Kotlin object format
        while (st?.step()!!) {
            val id = UUID.fromString(st.columnString(0))
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnString(3).toBoolean()
            claimList.add(Claim(id, title, date, isSolved))
        }
        return claimList
    }
}