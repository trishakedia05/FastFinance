//Scaffold(
//
//topBar = {
//    CenterAlignedTopAppBar(
//        modifier= Modifier.padding(vertical=1.dp),
//        navigationIcon = {
//            Row {
//                IconButton(onClick = { navController.navigate(all_Screens.SplashScreen.name) }) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "Localized description",
//                        tint= Color(0xFF3F5B60)
//                    )
//                }
//                Image(modifier= Modifier
//                    .size(53.dp)
//                    .padding(horizontal = 3.dp),
//                    painter = painterResource(id = R.drawable.app_logo), // Replace with your image resource
//                    contentDescription = null // Provide a meaningful content description
//                )
//            }
//
//        },
//        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = Color(0xFFDDB87D),
//            titleContentColor = Color(0xFF3F5B60)
//            // titleContentColor = MaterialTheme.colorScheme.primary,
//        ),
//        title = {
//
//            Text("   Flash Finance",modifier= Modifier.padding(horizontal =2.dp),
//                style= TextStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp,
//                    textAlign = TextAlign.Center)
//            )
//        },
//        actions = {
//            IconButton(modifier= Modifier.padding(horizontal=0.dp),onClick = { navController.navigate(
//                all_Screens.SettingScreen.name) }) {
//                Icon(
//                    imageVector = Icons.Filled.Star,
//                    contentDescription = "Localized description", tint= Color(0xFF3F5B60)
//
//                )
//            }
//        },
//        scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//    )
//},
//bottomBar =   {
//    BottomAppBar(modifier = Modifier.padding(2.dp),
//        containerColor = Color.White,
//        contentColor = Color.Black,
//        tonalElevation = 4.dp) {
//        Row() {
//            IconButton(onClick = { navController.navigate(all_Screens.SplashScreen.name) }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Localized description",
//                    tint = Color(0xFF3F5B60)
//                )
//            }
//            IconButton(onClick = {}) {
//                Icon(
//                    imageVector = Icons.Filled.Share,
//                    contentDescription = "Localized description",
//                    tint = Color.Black,
//
//                    )
//            }
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Outlined.Star,
//                    contentDescription = "Localized description",
//                    tint = Color.Black)
//            }
//
//        }
//    }
//}
//){
//
//}



//


//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth() // Occupy the full width of the parent container
//                                .padding(horizontal = 1.dp, vertical = 2.dp),
//                            horizontalArrangement = Arrangement.End,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            IconButton(onClick = {
//
//                                if (sendIntent.resolveActivity(context.packageManager) != null) {
//                                    shareLauncher.launch(sendIntent)
//                                } else {
//                                    Toast.makeText(
//                                        context,
//                                        "No app to handle the share action",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Filled.Share,
//                                    contentDescription = "Localized description",
//                                    tint = Color.Black,
//
//                                    )
//                            }
//                            IconButton(onClick = { }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Star,
//                                    contentDescription = "Localized description",
//                                    tint = Color.Black,
////                                    modifier=Modifier.border(
////                                            BorderStroke(1.dp, Color.White),
////                                    shape = Shapes
////                                )
//
//                                    )
//                            }
//                            ClickableTextWithLink(
//                                text = "Read more..",
//                                link = "${item.url}",
//                                item = item
//                            ) }