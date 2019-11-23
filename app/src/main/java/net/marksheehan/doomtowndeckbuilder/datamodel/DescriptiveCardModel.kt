package net.marksheehan.doomtowndeckbuilder.datamodel

import androidx.lifecycle.MutableLiveData

data class DescriptiveCardModel(val name : MutableLiveData<String>, val description : MutableLiveData<String>)