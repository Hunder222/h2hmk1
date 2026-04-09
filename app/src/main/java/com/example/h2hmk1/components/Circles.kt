package com.example.h2hmk1.components

import android.R.attr.thickness
import com.example.h2hmk1.R
import android.R.attr.thumbOffset
import android.util.MutableBoolean
import android.widget.GridLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Vector
import kotlin.math.log

@Composable
fun circlesHome() {
    Text(
        "Dette er Elliot",
        fontSize = 40.sp,
        modifier = Modifier
            .padding(30.dp)
    )
}


val h2hPink: Color = Color(0xFFFE77B7)

val circleContactsList: MutableList<String> = mutableListOf(

)




@Preview
@Composable
fun CircleListPREVIEW(){
    var isListExtended by remember { mutableStateOf(true) }

    CircleList(
        circleName = "Placeholder Circle",
        contactsList = circleContactsList,
        isListExtended = isListExtended,
        onExpandClick = {isListExtended = it}
    )
}


@Composable
fun CircleList(
    circleName: String = "Hello World",
    contactsList: MutableList<String>,
    isListExtended: Boolean,
    onExpandClick: (Boolean) -> Unit
) {
    val amountOfContacts = contactsList.size

    var isSwitchActive by remember { mutableStateOf(false) }

    val listHeight by animateDpAsState(
        targetValue = if (isListExtended) 90.dp + (50.dp * amountOfContacts) else 50.dp
    )

    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors( containerColor = Color.White ),
        modifier = Modifier
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
                .padding(start = 15.dp, end = 10.dp, top = 3.dp)
        ) {
            Text(circleName, fontSize = 20.sp)
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
            btnIcon = Icons.Rounded.KeyboardArrowUp,
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