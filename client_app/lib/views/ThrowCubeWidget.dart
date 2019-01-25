import 'package:client_app/views/Refreshable.dart';
import 'package:flutter/material.dart';

class ThrowCubeWidget extends Refreshable {
  final void Function(Map<String, dynamic> sendMessage) sendMessage;
  ThrowCubeWidget(this.sendMessage);

  @override
  Widget build(BuildContext context) {
    return Container();
  }

  void createUiAfterClickButton(BuildContext context) {
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
                          sendMessage({"ACTION_TYPE": "THROW_CUBE"});
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

  @override
  void updateState(Map<String, dynamic> json) {
    // TODO: implement updateState
  }
}
