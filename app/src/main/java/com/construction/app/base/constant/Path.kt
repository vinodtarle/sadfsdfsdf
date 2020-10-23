package com.construction.app.base.constant

class Path {
    companion object {
        fun attendance() = "attendance"
        fun item() = "item"
        fun labour() = "labour"
        fun material() = "material"
        fun payment() = "payment"
        fun site() = "site"
        fun supplier() = "supplier"
        fun unit() = "unit"
        fun userId() = "testUserId"
        fun expenses(userId: String) = "/users/$userId/expenses"
    }
}
        
    
