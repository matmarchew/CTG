import 'package:client_app/Client.dart';
import 'package:client_app/action/GetBetTileAction.dart';
import 'package:client_app/action/PutBetCardAction.dart';
import 'package:client_app/action/PutDesertTileAction.dart';
import 'package:client_app/action/ThrowCubeAction.dart';
import 'package:client_app/state/Model.dart';
import 'package:client_app/views/main/MainView.dart';
import 'package:client_app/views/main/action/ButtonAction.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  final String title;
  final Client client;
  final Model model;

  MyHomePage({Key key, this.title, this.client, this.model}) : super(key: key);

  @override
  _MyHomePageState createState() {
    return _MyHomePageState(client, model);
  }
}

class _MyHomePageState extends State<MyHomePage> {
  final Client client;
  final Model model;
  MainView mainWidget;

  _MyHomePageState(this.client, this.model);

  void onTabTapped() {
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    mainWidget = MainView(model, [
      ButtonAction(ThrowCubeAction(client), context),
      ButtonAction(PutBetCardAction(), context),
      ButtonAction(GetBetTileAction(), context),
      ButtonAction(PutDesertTileAction(), context)
    ]);
    return Scaffold(appBar: AppBar(title: Text(widget.title)), body: mainWidget);
  }
}
