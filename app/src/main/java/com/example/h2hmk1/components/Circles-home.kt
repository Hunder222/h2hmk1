package com.example.h2hmk1.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowDown
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowUp
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h2hmk1.R
import com.example.h2hmk1.viewmodels.h2hViewmodel


val h2hPink: Color = Color(0xFFd086b3)

val circle1ContactsList: MutableList<String> = mutableListOf(
    "Mette",
    "Thomas",
    "Freja",
    "Lucas"
)

val circle2ContactsList: MutableList<String> = mutableListOf(
    "Emma",
    "Laura",
    "Mathilde",
    "Ida",
    "Sofie"
)


fun testCircles(
    viewmodel: h2hViewmodel
){
    viewmodel.createCircle("Family")

    circle1ContactsList.forEach { contact ->
        viewmodel.circles[0].friends.add(contact)
    }

    viewmodel.createCircle("Bestiessss <3")

    circle2ContactsList.forEach { contact ->
        viewmodel.circles[1].friends.add(contact)
    }
}



@Composable
fun CirclesHome(
    viewmodel: h2hViewmodel,
    newCircleBtn: () -> Unit,
    createJamBtn: () -> Unit,
    joinJamBtn: () -> Unit

) {
    LazyColumn(
        modifier = Modifier
            .padding(30.dp)
    ) {
        item {
            CirclesHeader()
            MyCircles(
                viewmodel,
                newCircleBtn
            )
            Jams(
                createJamBtn,
                joinJamBtn
            )
        }
    }
}

@Composable
fun CirclePopupLayout(
    createCircleBtn: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.DragHandle,
            contentDescription = "Drag handle"
        )
        Text(
            "Create Circle Name"
        )

        var circleName by remember { mutableStateOf("") }
        OutlinedTextField(
            value = circleName,
            onValueChange = { circleName = it },
            placeholder = {
                Text("Enter name...", color = Color.Black)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFFFE77B7),
                unfocusedBorderColor = Color(0xFFFFB6C1),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
    ) {

        Button(
            onClick = createCircleBtn,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFE77B7),
                contentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text(
                "Create circle"
            )
        }
    }
}
@Preview
@Composable
fun ContactPopupLayout(
    addContactBtn: () -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.DragHandle,
            contentDescription = "Drag handle"
        )
        Text(
            "Add your contacts to your circle!"
        )

        var contactName by remember { mutableStateOf("") }
        OutlinedTextField(
            value = contactName,
            onValueChange = { contactName = it },
            placeholder = {
                Text("e.g. Kathrine...", color = Color.Black)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFFFE77B7),
                unfocusedBorderColor = Color(0xFFFFB6C1),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
    ) {

        Button(
            onClick = addContactBtn,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFE77B7),
                contentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text(
                "Add contact"
            )
        }
    }
}

@Composable
fun CirclesHeader() {
    Text(
        "Circles",
        fontSize = 40.sp,
        modifier = Modifier
            .padding(bottom = 10.dp)
    )

    Text(
        "Your friends and loved ones, specify which people you need and when",
        color = Color(0XFFA3A3A3),
        fontSize = 20.sp
    )
}

@Composable
fun MyCircles(
    viewmodel: h2hViewmodel,
    newCircleBtn: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 20.dp)
    )
    {
        Text(
            "My circles",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 20.dp)

        )

        Button(
            onClick = newCircleBtn,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = h2hPink
            ),
            modifier = Modifier
                .padding(top = 15.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text(
                "New"
            )


        }
    }

    if (viewmodel.circles.isEmpty()) {
        Button(
            onClick = {testCircles(viewmodel)}
        ) {
            Text("Test")
        }
    }


    viewmodel.circles.forEach { circle ->
        CircleList(
            circleName = circle.name,
            contactsList = circle.friends,
            isListExtended = circle.isListExtended.value,
            onExpandClick = { circle.isListExtended.value = it }
        )
    }


//@Preview
//@Composable
//fun CircleListPREVIEW(){
//    var isListExtended by remember { mutableStateOf(true) }
//
//    CircleList(
//        circleName = "Placeholder",
//        contactsList = circle1ContactsList,
//        isListExtended = isListExtended,
//        onExpandClick = {isListExtended = it}
//    )



}


@Composable
fun CircleList(
    circleName: String = "Hello World",
    contactsList: MutableList<String>,
    isListExtended: Boolean,
    onExpandClick: (Boolean) -> Unit
) {
    val amountOfContacts = contactsList.size

    var isSwitchActive by remember { mutableStateOf(true) }

    val listHeight by animateDpAsState(
        targetValue = if (isListExtended) 90.dp + (50.dp * amountOfContacts) else 50.dp
    )

    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors( containerColor = Color.White ),
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(listHeight)
            .fillMaxWidth()
            .border(3.dp, h2hPink, RoundedCornerShape(25.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onExpandClick( !isListExtended )
            }
    ) {
        // List header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .padding(start = 20.dp, end = 8.dp, top = 3.dp)
        ) {
            Text(
                circleName,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(3f)
            )

            if (!isListExtended) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardDoubleArrowDown,
                    contentDescription = "Expand",
                    tint = h2hPink,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .size(height = 30.dp, width = 30.dp)
                        .weight(1f)
                )
            }

            ToggleSwitch(
                isChecked = isSwitchActive,
                onSwitchPressed = { isSwitchActive = it }
            )
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = h2hPink
        )

        // Contacts list
        contactsList.forEach { contact ->
            ContactListItem(contact)
        }

        // Bottom buttons
        CircleListSettingsBar(
            onExpandClick = { onExpandClick( !isListExtended ) }
        )
    }
}


@Composable
fun CircleListSettingsBar(
    onExpandClick: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(h2hPink)
    ) {
        ContactListBtn(
            btnText = "More Settings",
            btnIcon = Icons.Rounded.MoreHoriz,
            modifier = Modifier.weight(3f),
            onPressAction = {/*TODO*/}
        )

        VerticalDivider(
            color = Color.White,
            modifier = Modifier.height(25.dp),
            thickness = 2.dp
        )

        ContactListBtn(
            btnIcon = Icons.Rounded.KeyboardDoubleArrowUp,
            modifier = Modifier.weight(1f),
            onPressAction = onExpandClick
        )

        VerticalDivider(
            color = Color.White,
            modifier = Modifier.height(25.dp),
            thickness = 2.dp
        )

        ContactListBtn(
            btnText = "Add to Circle",
            btnIcon = Icons.Rounded.AddCircleOutline,
            modifier = Modifier.weight(3f),
            onPressAction = {/*TODO*/}
        )
    }
}


@Composable
fun ContactListBtn(
    btnText: String = "",
    btnIcon: ImageVector,
    modifier: Modifier,
    onPressAction: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onPressAction()
            }
    ) {
        Icon(
            imageVector = btnIcon,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier
                .padding(vertical = 2.dp)
        )
        if (btnText != "") Text(
            btnText,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}


@Composable
fun ContactListItem(
    contactName: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Avatar of contact",
            modifier = Modifier
                .padding(vertical = 2.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Text(
            contactName,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )

        Icon(
            imageVector = Icons.Rounded.MoreHoriz,
            contentDescription = "More settings for ${contactName}",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    /*TODO*/
                }
        )
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = h2hPink
    )
}










@Preview
@Composable
fun ToggleSwitchPREVIEW(){
    var isSwitchActive by remember { mutableStateOf(false) }

    ToggleSwitch(
        isChecked = isSwitchActive,
        onSwitchPressed = { isSwitchActive = it }
    )
}

@Composable
fun ToggleSwitch(
    isChecked: Boolean,
    onSwitchPressed: (Boolean) -> Unit
){
    val switchBgColor by animateColorAsState(
        targetValue = if (isChecked) h2hPink else Color.White
    )
    val switchNubColor by animateColorAsState(
        targetValue = if (isChecked) Color.White else h2hPink
    )
    val nubOffset by animateDpAsState(
        targetValue = if (isChecked) 35.dp else 0.dp
    )

    Box(
        modifier = Modifier
            .size(width = 70.dp, height = 35.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = h2hPink, shape = CircleShape)
            .background(color = switchBgColor)
            .padding(2.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onSwitchPressed( !isChecked )
            }
    ) {
        Box(
            modifier = Modifier
                .offset(nubOffset)
                .size(35.dp-(2.dp*2))
                .clip(CircleShape)
                .background(switchNubColor)
        ){ }
    }
}








@Composable
fun Jams(
    createJamBtn: () -> Unit,
    joinJamBtn: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
    ) {
        Text(
            "Jam",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        Text(
            "Your temporary circle",
            color = Color(0XFFA3A3A3),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = createJamBtn,
                colors = ButtonDefaults.buttonColors(
                    containerColor = h2hPink
                ),
                modifier = Modifier
                    .width(150.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black
                    )
            ) {
                Text(
                    "Create a jam"
                )
            }

            Button(
                onClick = joinJamBtn,
                colors = ButtonDefaults.buttonColors(
                    containerColor = h2hPink
                ),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(150.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black
                    )
            ) {
                Text(
                    "Join a jam"
                )
            }
        }
    }
}