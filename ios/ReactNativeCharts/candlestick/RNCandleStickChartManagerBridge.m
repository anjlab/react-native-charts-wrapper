//  Created by xudong wu on 24/02/2017.
//  Copyright © 2017 wuxudong
//

#import "React/RCTViewManager.h"
#import "RNChartManagerBridge.h"
#import "RNYAxisChartManagerBridge.h"
#import "RNBarLineChartManagerBridge.h"

@interface RCT_EXTERN_MODULE(RNCandleStickChartManager, RCTViewManager)

EXPORT_BAR_LINE_CHART_BASE_PROPERTIES

RCT_EXTERN_METHOD(addEntry:(nonnull NSNumber *)name entry:(NSDictionary *)entry)
@end
