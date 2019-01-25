import 'package:client_app/Client.dart';
import 'package:client_app/navigation_bar/MyBottomNavigationBar.dart';
import 'package:client_app/state/Model.dart';
import 'package:client_app/views/PlaceholderWidget.dart';
import 'package:client_app/views/Refreshable.dart';
import 'package:client_app/views/ThrowCubeWidget.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  final Client client;
  final String title;
  final String login = "test";
  final Model model;
  final List<Refreshable> mainWindows = [];

  MyHomePage({Key key, this.title, this.client, this.model}) : super(key: key) {
    mainWindows.add(PlaceholderWidget(Colors.white));
    mainWindows.add(ThrowCubeWidget(client.sendMessage));
    mainWindows.add(PlaceholderWidget(Colors.green));
    mainWindows.add(PlaceholderWidget(Colors.blue));
    mainWindows.add(PlaceholderWidget(Colors.red));
  }

  @override
  _MyHomePageState createState() {
    client.sendMessage({"LOGIN": login});
    client.updateModel(model);
    return _MyHomePageState(mainWindows);
  }
}

class _MyHomePageState extends State<MyHomePage> {
  int currentIndex = 0;
  final List<Refreshable> mainWindows;

  _MyHomePageState(this.mainWindows);

  void onTabTapped(int index) {
    setState(() {
        currentIndex = index;
        mainWindows[currentIndex].createUiAfterClickButton(context);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: mainWindows[currentIndex],
        bottomNavigationBar: MyBottomNavigationBar(onTabTapped, currentIndex));
  }
}
