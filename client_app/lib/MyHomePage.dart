import 'package:client_app/Client.dart';
import 'package:client_app/navigation_bar/MyBottomNavigationBar.dart';
import 'package:client_app/state/Model.dart';
import 'package:client_app/views/PlaceholderWidget.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  final Client client;
  final String title;
  final String login = "test";
  final Model model;

  MyHomePage({Key key, this.title, this.client, this.model}) : super(key: key);

  @override
  _MyHomePageState createState() {
    client.sendMessage({"LOGIN":login});
    client.updateModel(model);
    return _MyHomePageState();
  }
}

class _MyHomePageState extends State<MyHomePage> {
  int currentIndex = 0;
  final List<Widget> mainWindows = [
    PlaceholderWidget(Colors.white),
    PlaceholderWidget(Colors.deepOrange),
    PlaceholderWidget(Colors.green),
    PlaceholderWidget(Colors.blue),
    PlaceholderWidget(Colors.red)
  ];

  void onTabTapped(int index) {
    setState(() {
      currentIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: mainWindows[currentIndex],
      bottomNavigationBar: MyBottomNavigationBar(onTabTapped, currentIndex)
    );
  }
}

