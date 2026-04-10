package com.example.h2hmk1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CirclesHome(
    newCircleBtn: () -> Unit,
    createJamBtn: () -> Unit,
    joinJamBtn: () -> Unit

) {
    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        CirclesHeader()
        MyCircles(
            newCircleBtn
        )
        Jams(
            createJamBtn,
            joinJamBtn
        )

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
    newCircleBtn: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
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
                contentColor = Color(0xFFFE77B7)
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
            "Jams",
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
                    containerColor = Color(0xFFFE77B7)
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
                    containerColor = Color(0xFFFE77B7)
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