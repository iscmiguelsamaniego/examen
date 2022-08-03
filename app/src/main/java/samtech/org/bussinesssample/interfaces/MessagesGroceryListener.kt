package samtech.org.bussinesssample.interfaces

import samtech.org.bussinesssample.database.entities.Groceries

interface MessagesGroceryListener {
    fun onDownloadComplete(groceries: Groceries?)
}