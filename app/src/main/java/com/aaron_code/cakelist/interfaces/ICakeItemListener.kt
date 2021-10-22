package com.aaron_code.cakelist.interfaces

import com.aaron_code.cakelist.model.Cake

interface ICakeItemListener {
    fun onItemClick(cake: Cake)
}