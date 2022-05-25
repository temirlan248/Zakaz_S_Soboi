package kz.iitu.zakaz_s_soboi.presentation

import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CategoryEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity

object DbInit {
    val restaurantList = listOf(
        RestaurantEntity(
            name = "McDonald’s",
            location = "улица Шевченко 85А",
            imageUrl = "https://www.tadviser.ru/images/thumb/2/28/Mfgdcdonalds.jpg/840px-Mfgdcdonalds.jpg"
        ),
        RestaurantEntity(
            name = "KFC",
            location = "Самал, 2-й микрорайон 111",
            imageUrl = "https://cdni.rt.com/russian/images/2022.03/article/62289150ae5ac907503cd749.jpg"
        ),
        RestaurantEntity(
            name = "Hardee's",
            location = "микрорайон Самал-2 111",
            imageUrl = "https://media-cdn.tripadvisor.com/media/photo-s/14/89/f9/4a/hardees-jumeira-rd-dubai.jpg"
        ),
        RestaurantEntity(
            name = "Burger King",
            location = "пр-т. Абая 89",
            imageUrl = "https://restolife.kz/upload/information_system_6/2/3/2/item_23259/information_items_property_28042.jpg"
        )
    )

    val categoryList = listOf(
        CategoryEntity(
            restaurantId = 1,
            name = "Burger"
        ),
        CategoryEntity(
            restaurantId = 1,
            name = "Combo"
        ),
        CategoryEntity(
            restaurantId = 1,
            name = "Pizza"
        ),
        //////////////////////
        CategoryEntity(
            restaurantId = 2,
            name = "Basket"
        ),
        CategoryEntity(
            restaurantId = 2,
            name = "Snacks"
        ),
        ///////////////////////////
        CategoryEntity(
            restaurantId = 3,
            name = "Burger"
        ),
        CategoryEntity(
            restaurantId = 3,
            name = "Combo"
        ),
        //////////////////////////
        CategoryEntity(
            restaurantId = 4,
            name = "Burger"
        ),
        CategoryEntity(
            restaurantId = 4,
            name = "Snacks"
        ),
        CategoryEntity(
            restaurantId = 4,
            name = "Drinks"
        )
    )

    val productList = listOf(

        // 1 6 8 - burger
        // 2 7 - combo
        // 3 - pizza
        // 4 - basket
        // 5 9 - snacks
        // 10 - drinks

        ProductEntity(
            categoryId = 1,
            name = "Burger1",
            description = "Burger1 description",
            imageUrl = "https://gippo.kz/wp-content/uploads/2021/12/%D0%91%D1%83%D1%80%D0%B3%D0%B5%D1%80-India.jpg",
            price = 1_000
        ),

        ProductEntity(
            categoryId = 1,
            name = "Burger2",
            description = "Burger2 description",
            imageUrl = "https://504-400.kz/upload/resize_cache/iblock/02c/768_427_0/02c7d84f51172b8f8dbf85015fb3b133.jpg",
            price = 20_000
        ),

        ProductEntity(
            categoryId = 6,
            name = "Burger3",
            description = "Burger3 description",
            imageUrl = "https://gippo.kz/wp-content/uploads/2021/12/%D0%91%D1%83%D1%80%D0%B3%D0%B5%D1%80-India.jpg",
            price = 13_000
        ),

        ProductEntity(
            categoryId = 8,
            name = "Burger4",
            description = "Burger4 description",
            imageUrl = "https://504-400.kz/upload/resize_cache/iblock/02c/768_427_0/02c7d84f51172b8f8dbf85015fb3b133.jpg",
            price = 203_000
        ),

        ProductEntity(
            categoryId = 2,
            name = "Combo1",
            description = "Burger + snacks + drink",
            imageUrl = "https://www.portablepress.com/wp-content/uploads/2017/10/Fast-food-combo-meal-1.jpg",
            price = 30_000
        ),

        ProductEntity(
            categoryId = 2,
            name = "Combo2",
            description = "burgerr + drink + snack",
            imageUrl = "https://cdn.winsightmedia.com/platform/files/public/cspdn/800x420/combo-meal-burger-fries-coke-885.jpg",
            price = 1
        ),

        ProductEntity(
            categoryId = 7,
            name = "Combo3",
            description = "Burger + snacks + drink",
            imageUrl = "https://www.portablepress.com/wp-content/uploads/2017/10/Fast-food-combo-meal-1.jpg",
            price = 309_000
        ),

        ProductEntity(
            categoryId = 7,
            name = "Combo4",
            description = "burgerr + drink + snack",
            imageUrl = "https://cdn.winsightmedia.com/platform/files/public/cspdn/800x420/combo-meal-burger-fries-coke-885.jpg",
            price = 8
        ),

        ProductEntity(
            categoryId = 3,
            name = "Pizza1",
            description = "pizzaaaaa",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg",
            price = 293_837
        ),

        ProductEntity(
            categoryId = 4,
            name = "basket1",
            description = "small basket",
            imageUrl = "https://m.kura.kz/images/2019-01/5c3b0f3ce95b1.png",
            price = 123_123
        ),

        ProductEntity(
            categoryId = 5,
            name = "snack1",
            description = "snaack1",
            imageUrl = "https://www.ubuy.vn/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvODEtVlhjNnhxWkwuX1NMMTUwMF8uanBn.jpg",
            price = 2
        ),

        ProductEntity(
            categoryId = 5,
            name = "snack2",
            description = "snaack2",
            imageUrl = "https://www.eatthis.com/wp-content/uploads/sites/4/2020/05/snacks-in-america.jpg",
            price = 4
        ),


        ProductEntity(
            categoryId = 9,
            name = "snack3",
            description = "snaack3",
            imageUrl = "https://www.ubuy.vn/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvODEtVlhjNnhxWkwuX1NMMTUwMF8uanBn.jpg",
            price = 20
        ),

        ProductEntity(
            categoryId = 9,
            name = "snack4",
            description = "snaack4",
            imageUrl = "https://www.eatthis.com/wp-content/uploads/sites/4/2020/05/snacks-in-america.jpg",
            price = 45
        ),

        ProductEntity(
            categoryId = 10,
            name = "vitamin C",
            description = "drink ",
            imageUrl = "https://dastarkhan24.kz/upload/iblock/31b/31b3a04bbbf58b31f7d1fec99467150b.jpg",
            price = 1_000
        )

    )
}