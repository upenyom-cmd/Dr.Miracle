package com.example.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TrackedMedication
import com.example.data.repository.CommonMedication
import com.example.data.repository.InteractionAlertResult
import com.example.data.repository.OfflineMedications

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MedicationTrackerScreen(viewModel: MedicalViewModel) {
    val trackedMeds by viewModel.trackedMedications.collectAsState()
    val interactions by viewModel.interactionAlerts.collectAsState()

    val nameText by viewModel.medNameInput.collectAsState()
    val dosageText by viewModel.medDosageInput.collectAsState()
    val timingText by viewModel.medTimingInput.collectAsState()
    val notesText by viewModel.medNotesInput.collectAsState()

    val focusManager = LocalFocusManager.current
    var isNewFormExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section title
        item {
            Column {
                Text(
                    text = "Regimen & Reminders",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Track your medications, configure dosage reminders, and receive clinical notifications of hazardous contraindications.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }
        }

        // 1. DRUG CONFLICT ALERT NETWORK PANEL (DYNAMIC)
        item {
            InteractionsAlertPanel(alerts = interactions, trackedCount = trackedMeds.size)
        }

        // Expandable / collapsible trigger button to introduce a medication
        item {
            Button(
                onClick = { isNewFormExpanded = !isNewFormExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("toggle_add_med_form"),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNewFormExpanded) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primary,
                    contentColor = if (isNewFormExpanded) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = if (isNewFormExpanded) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isNewFormExpanded) "Collapse Form" else "Track a New Medication",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Form fields to add new tracked medication
        if (isNewFormExpanded) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
                        .testTag("add_medication_form"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "NEW MEDICATION REGIMEN",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )

                        // Common database guide search picker
                        Text(
                            text = "Select from Common Clinical Database:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(OfflineMedications.medications) { drug ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(MaterialTheme.colorScheme.secondary)
                                        .clickable {
                                            viewModel.setMedicationNameFromCommon(drug.name)
                                        }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = drug.name,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))

                        // Custom fields input overrides
                        OutlinedTextField(
                            value = nameText,
                            onValueChange = { viewModel.medNameInput.value = it },
                            label = { Text("Medication Name (Generic or Brand)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("med_name_input"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                            )
                        )

                        OutlinedTextField(
                            value = dosageText,
                            onValueChange = { viewModel.medDosageInput.value = it },
                            label = { Text("Dosage Strength (e.g., 200 mg, 1 Tablet)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("med_dosage_input"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                            )
                        )

                        // Timing Options Helper Chips
                        Column {
                            Text(
                                text = "Reminder Schedule & Timing:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                            val timings = listOf("Morning", "Midday", "Evening", "Bedtime", "With Meals", "On-Demand (PRN)")
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(timings) { time ->
                                    val isSelected = timingText.contains(time)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondary)
                                            .clickable {
                                                viewModel.medTimingInput.value = time
                                            }
                                            .padding(horizontal = 10.dp, vertical = 5.dp)
                                    ) {
                                        Text(
                                            text = time,
                                            fontSize = 11.sp,
                                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        OutlinedTextField(
                            value = timingText,
                            onValueChange = { viewModel.medTimingInput.value = it },
                            label = { Text("Reminders Custom Schedule Details") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("med_timing_input"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                            )
                        )

                        OutlinedTextField(
                            value = notesText,
                            onValueChange = { viewModel.medNotesInput.value = it },
                            label = { Text("Clinical warnings / Instructions (Optional)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("med_notes_input"),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                            )
                        )

                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                viewModel.addMedication()
                                isNewFormExpanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("save_medication_button"),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            enabled = nameText.isNotBlank() && dosageText.isNotBlank() && timingText.isNotBlank()
                        ) {
                            Text("Save Programmed Reminder", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }

        // Active tracked medications logs listing
        item {
            Text(
                text = "ACTIVE TRACKED REGIMENS (${trackedMeds.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        }

        if (trackedMeds.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "No trackings in progress",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Add the medications you are taking or planning to take to setup scheduling and check safety cross-interactions instantly.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp,
                            modifier = Modifier.height(48.dp) // Bound the layout
                        )
                    }
                }
            }
        } else {
            items(trackedMeds) { med ->
                TrackedMedCard(medication = med, onDelete = { viewModel.removeMedication(med.id) })
            }
        }
    }
}

@Composable
fun TrackedMedCard(medication: TrackedMedication, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
            .testTag("medication_remind_card"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Medication,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = medication.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove tracking",
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = medication.dosage,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = medication.timing,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                if (medication.notes.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Guideline: ${medication.notes}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun InteractionsAlertPanel(alerts: List<InteractionAlertResult>, trackedCount: Int) {
    if (alerts.isEmpty()) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Safe",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = if (trackedCount < 2) {
                        "Add at least 2 medications to screen for potential drug-drug interactions automatically."
                    } else {
                        "Drug-drug interactions analyzed. No overlapping contraindications identified."
                    },
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp
                )
            }
        }
    } else {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("interaction_warning_module")
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Contraindication clinical alert",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "POTENTIAL HAZARDOUS DRUG INTERACTION DETECTED",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.error,
                        letterSpacing = 0.5.sp
                    )
                }

                alerts.forEach { alert ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${alert.medicationNameA} ✖ ${alert.medicationNameB}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MaterialTheme.colorScheme.error)
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = alert.severity,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                        Text(
                            text = alert.explanation,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.padding(top = 4.dp),
                            lineHeight = 14.sp
                        )
                    }
                }
            }
        }
    }
}
