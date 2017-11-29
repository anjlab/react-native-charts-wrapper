//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNCandleStickChartManager)
open class RNCandleStickChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNCandleStickChartView()
    return ins;
  }

  override open static func requiresMainQueueSetup() -> Bool {
    return true;
  }

  @objc(addEntry:entry:)
  func addEntry(name: NSNumber, entry: NSDictionary) -> Void {
    self.bridge.uiManager.addUIBlock { (uiManager, viewRegistry) in
      let view = viewRegistry![name] as! RNCandleStickChartView
      let value = entry as! [String:AnyObject]
      let e = CandleChartDataEntry(x: value["x"]?.doubleValue ?? 0,
                                   shadowH: value["shadowH"]?.doubleValue ?? 0,
                                   shadowL: value["shadowL"]?.doubleValue ?? 0,
                                   open: value["open"]?.doubleValue ?? 0,
                                   close: value["close"]?.doubleValue ?? 0,
                                   data: value as AnyObject?)

      view.chart.data?.addEntry(e, dataSetIndex: 0)
      view.chart.notifyDataSetChanged()
    }
  }
}
