import 'package:client_app/views/Refreshable.dart';
import 'package:flutter/material.dart';

class PlaceholderWidget extends Refreshable {
  final Color color;
  PlaceholderWidget(this.color);

  @override
  Widget build(BuildContext context) {
    return Container(
        color: color
    );
  }

  @override
  void updateState(Map<String, dynamic> json) {
    // TODO: implement updateState
  }

  @override
  void createUiAfterClickButton(BuildContext context) {
    // TODO: implement createUiAfterClickButton
  }
}