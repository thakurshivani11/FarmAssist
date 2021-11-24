package com.example.weather

class CropSearch {
    companion object{
        public fun getCropDetail(name:String):Crop{
            var cropData:Crop=Crop()
            when(name){
                "Wheat"->{
                    val imageId=R.drawable.wheat //write correct crop image
                    val desc="wheat, any of several species of cereal " +
                            "grasses of the genus Triticum (family Poaceae) " +
                            "and their edible grains. Wheat is one of the oldest " +
                            "and most important of the cereal crops. Of the thousands " +
                            "of varieties known, the most important are common wheat " +
                            "(Triticum aestivum), used to make bread; durum wheat " +
                            "(T. durum), used in making pasta (alimentary pastes)" +
                            " such as spaghetti and macaroni; and club wheat (T. compactum)," +
                            " a softer type, used for cake, crackers, cookies, pastries," +
                            " and flours. Additionally, some wheat is used by industry" +
                            " for the production of starch, paste, malt, dextrose, " +
                            "gluten, alcohol, and other products."

                    val season="Winter"
                    cropData=Crop(name,imageId,desc,season)
                }
                "Banana"->{
                    val imageId=R.drawable.banana //write correct crop image
                    val desc="A banana is an elongated, edible fruit – botanically a " +
                            "berry – produced by several kinds of large herbaceous " +
                            "flowering plants in the genus Musa. In some countries, " +
                            "bananas used for cooking may be called \"plantains\", " +
                            "distinguishing them from dessert bananas. The fruit is " +
                            "variable in size, color, and firmness, but is usually " +
                            "elongated and curved, with soft flesh rich in starch " +
                            "covered with a rind, which may be green, yellow, red, purple, " +
                            "or brown when ripe. The fruits grow in clusters hanging " +
                            "from the top of the plant. Almost all modern edible seedless" +
                            " (parthenocarp) bananas come from two wild species – " +
                            "Musa acuminata and Musa balbisiana. "

                    val season="Late Summer"
                    cropData=Crop(name,imageId,desc,season)
                }
                "Brinjal"->{
                    val imageId=R.drawable.brinjal //write correct crop image
                    val desc="Most commonly purple, the spongy, absorbent fruit is used in several cuisines. Typically used as a vegetable in cooking, it is a berry by botanical definition. As a member of the genus Solanum, it is related to the tomato, chili pepper, and potato, although those are of the New World while the eggplant is of the Old World. Like the tomato, its skin and seeds can be eaten, but, like the potato, it is usually eaten cooked. Eggplant is nutritionally low in macronutrient and micronutrient content, but the capability of the fruit to absorb oils and flavors into its flesh through cooking expands its use in the culinary arts."

                    val season="Winter"
                    cropData=Crop(name,imageId,desc,season)
                }
                "Gourd"->{
                    val imageId=R.drawable.gourd //write correct crop image
                    val desc="Gourd is occasionally used to describe crop plants in the family Cucurbitaceae, like pumpkins, cucumbers, squash, luffa, and melons.[1] More specifically, gourd refers to the fruits of plants in the two Cucurbitaceae genera Lagenaria and Cucurbita, or also to their hollow, dried-out shell There are many different gourds worldwide. The main plants referred to as gourds include several species from the genus Cucurbita (mostly native to North America, including the Malabar gourd and turban squash), Crescentia cujete (the tree gourd or calabash tree, native to the American tropics) and Lagenaria siceraria (bottle gourd, thought to be originally from Africa but present worldwide)"
                    val season="Spring to Early Summer"
                    cropData=Crop(name,imageId,desc,season)
                }
                "Ladyfinger"->{
                    val imageId=R.drawable.ladyfinger //write correct crop image
                    val desc="Okra or Okro (US: /ˈoʊkrə/, UK: /ˈɒkrə/), Abelmoschus esculentus, known in many English-speaking countries as ladies' fingers or ochro, is a flowering plant in the mallow family. It is valued for its edible green seed pods. It is a good source of minerals, vitamins, antioxidants, and fiber.[2] The geographical origin of okra is disputed, with supporters of West African, Ethiopian, and South Asian origins. The plant is cultivated in tropical, subtropical, and warm temperate regions around the world and is a notable part of the cuisine of the Southern United States as well as Middle Eastern cuisine and Indian cuisine."
                    val season="Summer & Winter"
                    cropData=Crop(name,imageId,desc,season)
                }
                "Ridged Gourd"->{
                    val imageId=R.drawable.rigded //write correct crop image
                    val desc="The scientific name of Ridge Gourd is “Lufa acutangula” and is also known as “Ribbed Gourd”. One of the major vegetable crops in Asia is Ridge Gourd. It is a vegetable that you can grow easily in containers. Ridge Gourd is a member of the  family and contains a lot of water in it. Ridge Gourds need very little attention and hence they are well suited for any garden. But with the help of this article on "
                    val season="Summer & Rainy"
                    cropData=Crop(name,imageId,desc,season)
                }
            }

            return cropData
        }
    }
}