using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Android.Content.PM;
using student_card.Services;

namespace student_card.Platforms.Android
{
    public class OrientationService : IOrientationService
    {
        public void SetOrientationLandscape()
        {
            var activity = Platform.CurrentActivity;
            activity.RequestedOrientation = ScreenOrientation.Landscape;
        }

        public void SetOrientationPortrait()
        {
            var activity = Platform.CurrentActivity;
            activity.RequestedOrientation = ScreenOrientation.Portrait;
        }

        public void ResetOrientation()
        {
            var activity = Platform.CurrentActivity;
            activity.RequestedOrientation = ScreenOrientation.Unspecified;
        }
    }
}
