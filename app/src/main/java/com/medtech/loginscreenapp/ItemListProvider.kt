package com.medtech.loginscreenapp

class ItemListProvider {

    fun provideList(): ArrayList<ItemModel> {
        val list = arrayListOf<ItemModel>()
        list.add(ItemModel("MSB","Business school"))
        list.add(ItemModel("MEDTECH","IT school"))
        list.add(ItemModel("ESPRIT","IT school"))
        list.add(ItemModel("SESAME","IT school"))
        list.add(ItemModel("FSEG","Economics school"))
        list.add(ItemModel("IPEIT","Foundation school"))
        list.add(ItemModel("IPEIM","Foundation school"))
        list.add(ItemModel("ENIT","Engineering school"))
        list.add(ItemModel("ENIS","Engineering school"))
        return list
    }
}