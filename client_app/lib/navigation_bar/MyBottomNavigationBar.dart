import 'package:client_app/navigation_bar/MyBottomNavigationBarItem.dart';
import 'package:flutter/material.dart';

class MyBottomNavigationBar extends BottomNavigationBar {
  MyBottomNavigationBar(void Function(int index) onTabTapped, int currentIndex) : super(
    iconSize: 48,
    type: BottomNavigationBarType.fixed,
    currentIndex: currentIndex,
    onTap: onTabTapped,
    items: [
      MyBottomNavigationBarItem(Icon(Icons.info), Text('info')),
      MyBottomNavigationBarItem(Icon(Icons.crop_rotate), Text('throw cube')),
      MyBottomNavigationBarItem(Icon(Icons.keyboard_arrow_up), Text('put bet card')),
      MyBottomNavigationBarItem(Icon(Icons.keyboard_arrow_down), Text('get bet tile')),
      MyBottomNavigationBarItem(Icon(Icons.crop_square), Text('put desert tile'))
    ],
  );
}