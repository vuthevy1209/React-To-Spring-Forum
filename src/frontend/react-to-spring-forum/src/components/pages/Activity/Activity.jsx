import ActivityList from "../../organisms/ActivityList/ActivityList";
import s from "./style.module.css";

const Activity = () => {
    return (
        <div className={s.container} id="activity">
            <ActivityList scrollableTarget="activity"/>
        </div>
    );
};

export default Activity;
