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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowDown
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowUp
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.h2hmk1.R
import com.example.h2hmk1.viewmodels.Circle
import com.example.h2hmk1.viewmodels.h2hViewmodel


val h2hPink: Color = Color(0xFFd086b3)
val h2hRemoveRed: Color = Color(0xFFAF4848)

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

val otherContactsList: MutableList<String> = mutableListOf(
    "Magnus",
    "Lærke",
    "Mads",
    "Alba",
    "Valdemar",
    "Agnete",
    "Rasmus",
    "Sille",
    "Mikkel",
    "Frederikke"
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CirclesHome(
    viewmodel: h2hViewmodel,
    createJamBtn: () -> Unit,
    joinJamBtn: () -> Unit
) {
    var showCirclePopup by remember { mutableStateOf(false) }
    var showContactPopup by remember { mutableStateOf(false) }
    var showContactSettingsPopup by remember { mutableStateOf(false) }
    var showCircleSettingsPopup by remember { mutableStateOf(false) }
    var showRenameCirclePopup by remember { mutableStateOf(false) }
    
    var selectedContactName by remember { mutableStateOf("") }
    
    val sheetState = rememberModalBottomSheetState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(30.dp)
        ) {
            item {
                CirclesHeader()
                MyCircles(
                    viewmodel,
                    newCircleBtn = { showCirclePopup = true },
                    onAddContactClick = { showContactPopup = true },
                    onContactSettingsClick = { contactName, circle ->
                        viewmodel.selectedCircle = circle
                        selectedContactName = contactName
                        showContactSettingsPopup = true
                    },
                    onCircleSettingsClick = { circle ->
                        viewmodel.selectedCircle = circle
                        showCircleSettingsPopup = true
                    }
                )
                Jams(
                    createJamBtn,
                    joinJamBtn
                )
            }
        }

        if (showCirclePopup) {
            ModalBottomSheet(
                onDismissRequest = { showCirclePopup = false },
                sheetState = sheetState,
                containerColor = Color.White
            ) {
                CirclePopupLayout(
                    viewmodel = viewmodel,
                    onCircleCreated = { showCirclePopup = false }
                )
            }
        }

        if (showContactPopup) {
            ModalBottomSheet(
                onDismissRequest = { showContactPopup = false },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = Color.White
            ) {
                ContactPopupLayout(
                    viewmodel = viewmodel,
                    onContactAdded = { showContactPopup = false }
                )
            }
        }

        if (showContactSettingsPopup) {
            ModalBottomSheet(
                onDismissRequest = { showContactSettingsPopup = false },
                sheetState = rememberModalBottomSheetState(),
                containerColor = Color.White
            ) {
                ContactSettingsPopupLayout(
                    contactName = selectedContactName,
                    onRemoveClick = {
                        viewmodel.selectedCircle?.friends?.remove(selectedContactName)
                        showContactSettingsPopup = false
                    }
                )
            }
        }

        if (showCircleSettingsPopup) {
            ModalBottomSheet(
                onDismissRequest = { showCircleSettingsPopup = false },
                sheetState = rememberModalBottomSheetState(),
                containerColor = Color.White
            ) {
                CircleSettingsPopupLayout(
                    circleName = viewmodel.selectedCircle?.name ?: "",
                    onRenameClick = {
                        showCircleSettingsPopup = false
                        showRenameCirclePopup = true
                    },
                    onRemoveClick = {
                        viewmodel.selectedCircle?.let { viewmodel.circles.remove(it) }
                        showCircleSettingsPopup = false
                    }
                )
            }
        }

        if (showRenameCirclePopup) {
            RenameCirclePopup(
                currentName = viewmodel.selectedCircle?.name ?: "",
                onDismiss = { showRenameCirclePopup = false },
                onSubmit = { newName ->
                    viewmodel.selectedCircle?.name = newName
                    showRenameCirclePopup = false
                }
            )
        }
    }
}

@Composable
fun CirclePopupLayout(
    viewmodel: h2hViewmodel,
    onCircleCreated: () -> Unit = {}
) {
    var circleName by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            "Create Circle Name",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )


        OutlinedTextField(
            value = circleName,
            onValueChange = { circleName = it },
            placeholder = {
                Text("Enter name...", color = Color.Black)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = h2hPink,
                unfocusedBorderColor = Color(0xFFFFB6C1),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )

        Button(
            onClick = {
                if (circleName.isNotBlank()) {
                    viewmodel.createCircle(circleName)
                    circleName = ""
                    onCircleCreated()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = h2hPink,
                contentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .padding(top = 10.dp)
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

@Composable
fun ContactPopupLayout(
    viewmodel: h2hViewmodel,
    onContactAdded: () -> Unit = {}
){
    var searchQuery by remember { mutableStateOf("") }
    val selectedContacts = remember { mutableStateListOf<String>() }

    val filteredContacts = otherContactsList.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            "Add your contacts to your circle!",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text("Search contacts...", color = Color.Black)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = h2hPink,
                unfocusedBorderColor = Color(0xFFFFB6C1),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            items(filteredContacts) { contact ->
                ContactSelectionItem(
                    contactName = contact,
                    isSelected = selectedContacts.contains(contact),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedContacts.add(contact)
                        } else {
                            selectedContacts.remove(contact)
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                if (selectedContacts.isNotEmpty()) {
                    viewmodel.selectedCircle?.friends?.addAll(selectedContacts)
                    onContactAdded()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = h2hPink,
                contentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .padding(top = 10.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text(
                "Add selected contacts"
            )
        }
    }
}

@Composable
fun ContactSelectionItem(
    contactName: String,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isSelected) }
            .padding(vertical = 8.dp, horizontal = 10.dp)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = h2hPink
            )
        )
        Text(
            text = contactName,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun ContactSettingsPopupLayout(
    contactName: String,
    onRemoveClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Settings for $contactName",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Button(
            onClick = onRemoveClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = h2hRemoveRed,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text("Remove contact")
        }
    }
}

@Composable
fun CircleSettingsPopupLayout(
    circleName: String,
    onRenameClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Settings for $circleName",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Button(
            onClick = onRenameClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = h2hPink,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text("Change Circle Name")
        }

        Button(
            onClick = onRemoveClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = h2hRemoveRed,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )
        ) {
            Text("Remove Circle")
        }
    }
}

@Composable
fun RenameCirclePopup(
    currentName: String,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var newName by remember { mutableStateOf(currentName) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "Rename Circle",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = h2hPink,
                        unfocusedBorderColor = h2hPink
                    )
                )

                Button(
                    onClick = { if (newName.isNotBlank()) onSubmit(newName) },
                    colors = ButtonDefaults.buttonColors(containerColor = h2hPink),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text("Submit")
                }
            }
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
    newCircleBtn: () -> Unit,
    onAddContactClick: () -> Unit,
    onContactSettingsClick: (String, Circle) -> Unit,
    onCircleSettingsClick: (Circle) -> Unit
) {

    LaunchedEffect(Unit) {
        if (viewmodel.circles.isEmpty()) {
            testCircles(viewmodel)
        }
    }

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


    viewmodel.circles.forEach { circle ->
        CircleList(
            circle = circle,
            onExpandClick = { circle.isListExtended.value = it },
            onAddContactClick = {
                viewmodel.selectedCircle = circle
                onAddContactClick()
            },
            onContactSettingsClick = { contactName ->
                onContactSettingsClick(contactName, circle)
            },
            onCircleSettingsClick = {
                onCircleSettingsClick(circle)
            }
        )
    }
}


@Composable
fun CircleList(
    circle: Circle,
    onExpandClick: (Boolean) -> Unit,
    onAddContactClick: () -> Unit,
    onContactSettingsClick: (String) -> Unit,
    onCircleSettingsClick: () -> Unit
) {
    val amountOfContacts = circle.friends.size
    val isListExtended = circle.isListExtended.value

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
                circle.name,
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
        circle.friends.forEach { contact ->
            ContactListItem(
                contactName = contact,
                onMoreSettingsClick = { onContactSettingsClick(contact) }
            )
        }

        // Bottom buttons
        CircleListSettingsBar(
            onExpandClick = { onExpandClick( !isListExtended ) },
            onAddContactClick = onAddContactClick,
            isListExtended = isListExtended,
            onCircleSettingsClick = onCircleSettingsClick
        )
    }
}


@Composable
fun CircleListSettingsBar(
    onExpandClick: () -> Unit,
    onAddContactClick: () -> Unit,
    isListExtended: Boolean,
    onCircleSettingsClick: () -> Unit
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
            onPressAction = onCircleSettingsClick,
            enabled = isListExtended
        )

        VerticalDivider(
            color = Color.White,
            modifier = Modifier.height(25.dp),
            thickness = 2.dp
        )

        ContactListBtn(
            btnIcon = Icons.Rounded.KeyboardDoubleArrowUp,
            modifier = Modifier.weight(1f),
            onPressAction = onExpandClick,
            enabled = isListExtended
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
            onPressAction = onAddContactClick,
            enabled = isListExtended
        )
    }
}


@Composable
fun ContactListBtn(
    btnText: String = "",
    btnIcon: ImageVector,
    modifier: Modifier,
    onPressAction: () -> Unit,
    enabled: Boolean = true
){
    val clickableModifier = if (enabled) {
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            onPressAction()
        }
    } else Modifier

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .alpha(if (enabled) 1f else 0.5f)
            .then(clickableModifier)
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
    contactName: String,
    onMoreSettingsClick: () -> Unit
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
                    onMoreSettingsClick()
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