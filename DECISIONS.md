## System Design

I implemented the Alert service, which is responsible for receiving SOS alerts from devices and storing them in the database.

### Request Flow

1. Groups are registered with the members
2. Orders are placed according to the groups
3. Device are registered.
4. A device is assigned through order and group
5. An alert is sent from the device and is saved to the database.
6. If the alert is pending 5 minutes earlier the schedule hit every minute and change status to escalated.
7. After the alert is ack Knowledge the status changed to ack_knowledge
8. The device is return to its respective place after finishing trek.


The current implementation ends after storing the alert. In a complete system, another service would consume the stored alerts and handle coordinator assignment, notifications, and escalation.

### Layers

- API Layer – Accepts incoming alert requests and validates them.
- Service Layer – Contains the business logic for creating alerts.
- Repository/Data Access Layer – Handles communication with the database.
- Database – Stores alert records.

## Data Model

The primary entity I implemented is the **Alert** model.

### Alert

The Alert model stores every SOS event received from a device. It includes fields such as:

- `uuidCode` - uniquely generated
- `deviceId`- Identifies the device that sends alert.
- `latitude`- coordinates from the device
- `longitude`- coordinates from the device
- `createdAt`- created timestamp from the device
- `updatedAt`- updated timestamp when update
- `status` – Current state of the alert (e.g., pending, acknowledged, resolved).


I designed the Alert model to capture all information needed to record an incoming event while remaining independent of business workflows such as assignment or escalation.

#### Device ambiguity

When a new order is created, its status is NOT_ASSIGNED.
Before assigning a device, the system checks:
If the order is already ASSIGNED, it throws an exception.
If the device is already ASSIGNED, it throws an exception.
Only when both the order and the device are available does the system create the assignment and update their status.

This means that at any given time:

One order can have only one assigned device.
One device can be assigned to only one active order.

Therefore, when an alert comes from a device, the system can identify the correct order through the AssignDevice record.

### Deduplication
The system uses a 5-minute timestamp limit to identify retransmitted SOS messages.
The 5-minute limit helps remove duplicate alerts caused by network delays or message retries. It is long enough to catch common retransmissions but short enough to allow new SOS events to be detected.

### Escalation
I used scheduling for every minutes the api hit createdAt before and pending status
and changes status to Escalated.

### concurrency handling
Before assigning a device or coordinator, the system checks the current status of the order and device. If the status is already ASSIGNED, the request is rejected. If the status is available, it is updated to ASSIGNED and the assignment is saved.

### Unsure about
Two coordinates claiming alert same time when api hit



