import 'package:client_app/Client.dart';
import 'package:client_app/action/Action.dart';
import 'package:flutter/material.dart';

class ThrowCubeAction implements Action {
  final Client client;

  ThrowCubeAction(this.client);

  @override
  void doSpecialBehavior(BuildContext context) {
    Future.delayed(
        Duration.zero,
        () => showDialog(
            context: context,
            builder: (BuildContext context) {
              return AlertDialog(
                  content: Text("ARE YOU SURE THROW CUBE?"),
                  actions: <Widget>[
                    FlatButton(
                        child: Text("YES"),
                        onPressed: () {
                          client.sendMessage({"ACTION_TYPE": "THROW_CUBE"});
                          Navigator.pop(context);
                        }),
                    FlatButton(
                        child: Text("NO"),
                        onPressed: () {
                          Navigator.pop(context);
                        })
                  ]);
            }));
  }
}
